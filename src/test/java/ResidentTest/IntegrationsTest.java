package ResidentTest;


import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;
import org.junit.Test;
import org.junit.Assert.*;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class IntegrationsTest {

    ResidentRepositoryStub Test = new ResidentRepositoryStub();

    Resident Yunus = new Resident("Yunus","Antil","Großhausberg 2","Furtwangen",new Date(1995,07,22));
    Resident Robin = new Resident("Robin","Glatz","Gerberweg 5","Furtwangen",new Date(1994,01,18));
    Resident Tobi = new Resident("Tobias","Hauser","Großhausberg 2","Furtwangen",new Date(1996,03,25));

    @Test
    public void getFilteredResidentsListTest(){

        BaseResidentService baseResidentService = new BaseResidentService();

        Test.addResident(Yunus);
        Test.addResident(Robin);
        Test.addResident(Tobi);

        Resident filter = new Resident("Yunus","","","", null);
        Resident filter2 = new Resident("","","Großhausberg 2","", null);


        baseResidentService.setResidentRepository(Test);


        try {

            assertEquals(Yunus, baseResidentService.getUniqueResident(Yunus));
            assertEquals(Robin, baseResidentService.getUniqueResident(Robin));
            assertEquals(Tobi, baseResidentService.getUniqueResident(Tobi));

        } catch (ResidentServiceException e) {
            e.printStackTrace();
            fail("Liste ist fehlerhaft.");
        }

        List<Resident> filteredList = baseResidentService.getFilteredResidentsList(filter);

        try {
            assertEquals(1, filteredList.size());
            assertEquals(Yunus, filteredList.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Resident> filteredList2 = baseResidentService.getFilteredResidentsList(filter2);

        try {
            assertEquals(2,filteredList2.size());
            assertEquals(Yunus, filteredList2.get(0));
            assertEquals(Tobi, filteredList2.get(1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getUniqueResidentTest(){

        //Implementieren

    }

}
