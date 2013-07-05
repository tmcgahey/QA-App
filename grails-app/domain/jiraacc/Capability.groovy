package jiraacc

class Capability {

	static belongsTo = [attribute:Attribute, component:Component]
	
	String description
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		description(blank:false, maxSize:1000)
    }
	
	String toString() {
		return description
	}
}
