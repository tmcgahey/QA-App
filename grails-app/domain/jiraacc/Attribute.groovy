package jiraacc

class Attribute {
	
	static hasMany = [capabilities:Capability]
	
	String name
	
    static constraints = {
		name(blank:false, maxSize:200)
    }
}
