package jiraacc

class Component {
	
	static hasMany = [capabilities:Capability]
	
	String name
	
    static constraints = {
		name(blank:false, maxSize:200)
    }
}
