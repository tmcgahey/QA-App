package jiraacc

import static org.junit.Assert.*
import org.junit.*

class CapabilityIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
		def attr = new Attribute(name:"test attribute")
		attr.save()
		
		def comp = new Component(name:"test component")
		comp.save()
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void CanSaveCapability() {
        def cap = new Capability(description:"test capability")
		cap.save()
    }
	
	@Test
	void CapabilityDescriptionCanNotBeNull() {
		def cap = new Capability()
		
		def attr = Attribute.findByName("test attribute")
		cap.attribute = attr
		
		def comp = Component.findByName("test component")
		cap.component = comp
	
		assertFalse("A null Capability name should not be valid", cap.validate())
		assertTrue("A null Capability name should cause an error", cap.hasErrors())
		
		println "\nErrors:"
		println cap.errors ?: "no errors found"
		
		def badField = cap.errors.getFieldError('description')
		println "\nBadField:"
		println badField ?: "description wasn't a bad field"
		assertNotNull "Expecting to find an error on the description field", badField
		
	}
	
	@Test
	void CapabilityAttributeCanNotBeNull() {
		def cap = new Capability(description:"test desc")
				
		def comp = Component.findByName("test component")
		cap.component = comp
	
		assertFalse("A null attribute should not be valid", cap.validate())
		assertTrue("A null attribute should cause an error", cap.hasErrors())
		
		println "\nErrors:"
		println cap.errors ?: "no errors found"
		
		def badField = cap.errors.getFieldError('attribute')
		println "\nBadField:"
		println badField ?: "description wasn't a bad field"
		assertNotNull "Expecting to find an error on the attribute field", badField
		
	}
	
	@Test
	void CapabilityComponentCanNotBeNull() {
		def cap = new Capability(description:"test desc")
		
		def attr = Attribute.findByName("test attribute")
		cap.attribute = attr
		
		assertFalse("A null component should not be valid", cap.validate())
		assertTrue("A null component should cause an error", cap.hasErrors())
		
		println "\nErrors:"
		println cap.errors ?: "no errors found"
		
		def badField = cap.errors.getFieldError('component')
		println "\nBadField:"
		println badField ?: "description wasn't a bad field"
		assertNotNull "Expecting to find an error on the component field", badField
		
	}
}
