package jiraacc

import static org.junit.Assert.*
import org.junit.*

class AttributeIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void CanSaveAnAttribute() {
        def attr = new Attribute(Name:"test attribute")
		attr.save()
    }
	
	@Test
	void AttributeNameCanNotBeNull() {
		def attr = new Attribute()
		assertFalse("A null Attribute name should not be valid", attr.validate())
		assertTrue("A null Attribtute name should cause an error", attr.hasErrors())
		
		println "\nErrors:"
		println attr.errors ?: "no errors found"
		
		def badField = attr.errors.getFieldError('name')
		println "\nBadField:"
		println badField ?: "name wasn't a bad field"
		assertNotNull "Expecting to find an error on the name field", badField
		
	}
	
	@Test
	void AttributeNamesMustBeUnique() {
		def attr = new Attribute(name:"attr1")
		attr.save()
		
		def attr2 = new Attribute(name:"attr1")
		
		assertFalse("A null Attribute name should not be valid", attr2.validate())
		assertTrue("A null Attribtute name should cause an error", attr2.hasErrors())
		
		println "\nErrors:"
		println attr2.errors ?: "no errors found"
		
		def badField = attr2.errors.getFieldError('name')
		println "\nBadField:"
		println badField ?: "name wasn't a bad field"
		assertNotNull "Expecting to find an error on the name field", badField
		
	}
}
