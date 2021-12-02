package ResidentTest;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

import java.util.ArrayList;
import java.util.List;


public class ResidentRepositoryStub implements ResidentRepository {
    List<Resident> Stub = new ArrayList<>();

    public void addResident(Resident n){
        Stub.add(n);
    }


    @Override
    public List<Resident> getResidents() {
        return Stub;
    }
}
