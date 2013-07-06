<%@ page import="jiraacc.Attribute" %>



<div class="fieldcontain ${hasErrors(bean: attributeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="attribute.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="200" required="" value="${attributeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: attributeInstance, field: 'capabilities', 'error')} ">
	<label for="capabilities">
		<g:message code="attribute.capabilities.label" default="Capabilities" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${attributeInstance?.capabilities?}" var="c">
    <li><g:link controller="capability" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="capability" action="create" params="['attribute.id': attributeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'capability.label', default: 'Capability')])}</g:link>
</li>
</ul>

</div>

