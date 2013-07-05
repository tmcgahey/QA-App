package jiraacc

import java.util.Date;

class Attribute {
	
	static hasMany = [capabilities:Capability]
	
	String name
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		name(blank:false, maxSize:200, unique:true)
    }
	
	String toString() {
		return name
	}
}
