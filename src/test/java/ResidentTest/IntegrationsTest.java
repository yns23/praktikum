package ResidentTest;


import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;
import org.junit.Test;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class IntegrationsTest {

    ResidentRepositoryStub test = new ResidentRepositoryStub();

    Resident yunus = new Resident("Yunus","Antil","Großhausberg 2","Furtwangen", new Date(1995,07,22));
    Resident robin = new Resident("robin","Glatz","Gerberweg 5","Furtwangen", new Date(1994,01,18));
    Resident tobi = new Resident("Tobias","Hauser","Großhausberg 2","Furtwangen", new Date(1996,03,25));

    @Test
    public void getFilteredResidentsListTest(){

        BaseResidentService baseResidentService = new BaseResidentService();

        test.addResident(yunus);
        test.addResident(robin);
        test.addResident(tobi);

        Resident filter = new Resident("yunus","","","", null);
        Resident filter2 = new Resident("","","Großhausberg 2","", null);


        baseResidentService.setResidentRepository(test);


        try {

            assertEquals(yunus, baseResidentService.getUniqueResident(yunus));
            assertEquals(robin, baseResidentService.getUniqueResident(robin));
            assertEquals(tobi, baseResidentService.getUniqueResident(tobi));

        } catch (ResidentServiceException e) {
            e.printStackTrace();
            fail("Liste ist fehlerhaft.");
        }

        List<Resident> filteredList = baseResidentService.getFilteredResidentsList(filter);

        try {
            assertEquals(1, filteredList.size());
            assertEquals(yunus, filteredList.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Resident> filteredList2 = baseResidentService.getFilteredResidentsList(filter2);

        try {
            assertEquals(2,filteredList2.size());
            assertEquals(yunus, filteredList2.get(0));
            assertEquals(tobi, filteredList2.get(1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getUniqueResidentTest() throws ResidentServiceException {

        BaseResidentService baseResidentService = new BaseResidentService();
        baseResidentService.setResidentRepository(test);


        test.addResident(yunus);
        test.addResident(robin);
        test.addResident(tobi);

        Resident yunusTest = new Resident("Yunus","Antil","Großhausberg 2","Furtwangen", new Date(1995,07,22));
        Resident robinTest = new Resident("robin","Glatz","Gerberweg 5","Furtwangen", new Date(1994,01,18));
        Resident tobiTest = new Resident("Tobias","Hauser","Großhausberg 2","Furtwangen", new Date(1996,03,25));
        Resident fail = new Resident("Tobias","Baum","Großhausberg 2","Furtwangen", new Date(1996,03,25));
        Resident filter = new Resident("Yunus","","","",null);
        baseResidentService.setResidentRepository(test);

        assertEquals(yunus, baseResidentService.getUniqueResident(yunusTest));
        assertEquals(robin, baseResidentService.getUniqueResident(robinTest));
        assertEquals(tobi, baseResidentService.getUniqueResident(tobiTest));


        try {
            assertEquals(tobi, baseResidentService.getUniqueResident(fail));
        } catch (ResidentServiceException e) {
            e.printStackTrace();
        }

    }

}
