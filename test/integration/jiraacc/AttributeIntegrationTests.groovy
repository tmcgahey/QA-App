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
		
	}
}
