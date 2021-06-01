package m2.miage.m2gestionmembres.services;

import m2.miage.m2gestionmembres.entities.Membre;
import org.springframework.stereotype.Service;

@Service
public class RightServiceImpl implements IRightService{
    @Override
    public boolean checkRight(Membre mebre, String right) {
        if (right.equals(mebre.getType())){
            return true;
        }
        return false;
    }
}
