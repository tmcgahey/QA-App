package jiraacc

class Capability {

	static belongsTo = [attibute:Attribute, component:Component]
	
	String description
	Date createdDate
	
    static constraints = {
		description(blank:false, maxSize:1000)
    }
}
