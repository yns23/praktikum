package ResidentTest;
import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;
import org.junit.Test;


import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResidentRepositoryMock {

    BaseResidentService baseResidentService = new BaseResidentService();
    List<Resident> list = new ArrayList<Resident>();

    Resident yunus = new Resident("Yunus","Antil","Großhausberg 2","Furtwangen", new Date(1995,07,22));
    Resident robin = new Resident("robin","Glatz","Gerberweg 5","Furtwangen", new Date(1994,01,18));
    Resident tobi = new Resident("Tobias","Hauser","Großhausberg 2","Furtwangen", new Date(1996,03,25));

    @Test
    public void getFilteredResidentsListTest() throws ResidentServiceException{

        ResidentRepository residentRepository = createMock(ResidentRepository.class);

        list.add(yunus);
        list.add(robin);
        list.add(tobi);
        expect(residentRepository.getResidents()).andReturn(list).times(3);
        baseResidentService.setResidentRepository(residentRepository);
        replay(residentRepository);

        Resident suchMatixStraßeFamilyName = new Resident("", "A*","" , "", null);
        Resident suchMatixStraßeFirstName = new Resident("Y*", "","" , "", null);
        Resident suchMatixStraße = new Resident("", "","G*" , "", null);
        Resident filter2 = new Resident("","","Großhausberg 2","", null);


        List<Resident> listMatrix = baseResidentService.getFilteredResidentsList(suchMatixStraßeFamilyName);
        assertThat(listMatrix.get(0).getFamilyName(), is(yunus.getFamilyName()));

        listMatrix = baseResidentService.getFilteredResidentsList(suchMatixStraßeFirstName);
        assertThat(listMatrix.get(0).getFamilyName(), is(yunus.getFamilyName()));

        listMatrix = baseResidentService.getFilteredResidentsList(suchMatixStraße);
        assertThat(listMatrix.get(0).getStreet(), is(yunus.getStreet()));


        verify(residentRepository);
    }



    @Test
    public void getUniqueResidentTest() throws ResidentServiceException {
        ResidentRepository residentRepository = createMock(ResidentRepository.class);
        expect(residentRepository.getResidents()).andReturn(list);

        list.add(yunus);
        list.add(robin);
        list.add(tobi);

        expect(residentRepository.getResidents()).andReturn(list).times(3);
        baseResidentService.setResidentRepository(residentRepository);
        replay(residentRepository);

        Resident yunusTest = new Resident("Yunus","Antil","Großhausberg 2","Furtwangen", new Date(1995,07,22));
        Resident robinTest = new Resident("robin","Glatz","Gerberweg 5","Furtwangen", new Date(1994,01,18));
        Resident tobiTest = new Resident("Tobias","Hauser","Großhausberg 2","Furtwangen", new Date(1996,03,25));
        Resident fail = new Resident("Tobias","Baum","Großhausberg 2","Furtwangen", new Date(1996,03,25));
        Resident filter = new Resident("Yunus","","","",null);
        baseResidentService.setResidentRepository(residentRepository);

        assertEquals(yunus, baseResidentService.getUniqueResident(yunusTest));
        assertEquals(robin, baseResidentService.getUniqueResident(robinTest));
        assertEquals(tobi, baseResidentService.getUniqueResident(tobiTest));

    }
}
