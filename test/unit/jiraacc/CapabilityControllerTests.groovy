package jiraacc



import org.junit.*
import grails.test.mixin.*

@TestFor(CapabilityController)
@Mock(Capability)
class CapabilityControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/capability/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.capabilityInstanceList.size() == 0
        assert model.capabilityInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.capabilityInstance != null
    }

    void testSave() {
        controller.save()

        assert model.capabilityInstance != null
        assert view == '/capability/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/capability/show/1'
        assert controller.flash.message != null
        assert Capability.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/capability/list'

        populateValidParams(params)
        def capability = new Capability(params)

        assert capability.save() != null

        params.id = capability.id

        def model = controller.show()

        assert model.capabilityInstance == capability
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/capability/list'

        populateValidParams(params)
        def capability = new Capability(params)

        assert capability.save() != null

        params.id = capability.id

        def model = controller.edit()

        assert model.capabilityInstance == capability
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/capability/list'

        response.reset()

        populateValidParams(params)
        def capability = new Capability(params)

        assert capability.save() != null

        // test invalid parameters in update
        params.id = capability.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/capability/edit"
        assert model.capabilityInstance != null

        capability.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/capability/show/$capability.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        capability.clearErrors()

        populateValidParams(params)
        params.id = capability.id
        params.version = -1
        controller.update()

        assert view == "/capability/edit"
        assert model.capabilityInstance != null
        assert model.capabilityInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/capability/list'

        response.reset()

        populateValidParams(params)
        def capability = new Capability(params)

        assert capability.save() != null
        assert Capability.count() == 1

        params.id = capability.id

        controller.delete()

        assert Capability.count() == 0
        assert Capability.get(capability.id) == null
        assert response.redirectedUrl == '/capability/list'
    }
}
