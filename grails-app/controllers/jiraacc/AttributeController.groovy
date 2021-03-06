package jiraacc

import org.springframework.dao.DataIntegrityViolationException

class AttributeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [attributeInstanceList: Attribute.list(params), attributeInstanceTotal: Attribute.count()]
    }

    def create() {
        [attributeInstance: new Attribute(params)]
    }

    def save() {
        def attributeInstance = new Attribute(params)
        if (!attributeInstance.save(flush: true)) {
            render(view: "create", model: [attributeInstance: attributeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'attribute.label', default: 'Attribute'), attributeInstance.id])
        redirect(action: "show", id: attributeInstance.id)
    }

    def show(Long id) {
        def attributeInstance = Attribute.get(id)
        if (!attributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'attribute.label', default: 'Attribute'), id])
            redirect(action: "list")
            return
        }

        [attributeInstance: attributeInstance]
    }

    def edit(Long id) {
        def attributeInstance = Attribute.get(id)
        if (!attributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'attribute.label', default: 'Attribute'), id])
            redirect(action: "list")
            return
        }

        [attributeInstance: attributeInstance]
    }

    def update(Long id, Long version) {
        def attributeInstance = Attribute.get(id)
        if (!attributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'attribute.label', default: 'Attribute'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (attributeInstance.version > version) {
                attributeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'attribute.label', default: 'Attribute')] as Object[],
                          "Another user has updated this Attribute while you were editing")
                render(view: "edit", model: [attributeInstance: attributeInstance])
                return
            }
        }

        attributeInstance.properties = params

        if (!attributeInstance.save(flush: true)) {
            render(view: "edit", model: [attributeInstance: attributeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'attribute.label', default: 'Attribute'), attributeInstance.id])
        redirect(action: "show", id: attributeInstance.id)
    }

    def delete(Long id) {
        def attributeInstance = Attribute.get(id)
        if (!attributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'attribute.label', default: 'Attribute'), id])
            redirect(action: "list")
            return
        }

        try {
            attributeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'attribute.label', default: 'Attribute'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'attribute.label', default: 'Attribute'), id])
            redirect(action: "show", id: id)
        }
    }
}
