package com.example.omar.Service;

import com.example.omar.Entity.Bloc;
import com.example.omar.Entity.Chambre;
import com.example.omar.Repository.BlocRepository;
import com.example.omar.Repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlocServiceImp implements BlocService{

    private final BlocRepository blocRepository;

    private final ChambreRepository chambreRepository;

    @Autowired
    public BlocServiceImp(BlocRepository blocRepository, ChambreRepository chambreRepository){
        this.blocRepository=blocRepository;
        this.chambreRepository=chambreRepository;
    }
    @Override
    public Bloc createBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> getAllBloc() {
        return blocRepository.findAll();
    }

    @Override
    public Optional<Bloc>  getBlocById(long id) {
        return blocRepository.findById(id);
    }
    @Override
    public Bloc updateBloc(Bloc bloc, long id) {
        return blocRepository.save(bloc);
    }

    @Override
    public void deleteBloc(long id) {
        blocRepository.deleteById(id);
    }

    //V1
    @Override
    public Bloc afffecterChambresABloc (List<Long> numChambre, String nomBloc){
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);

        for (Long numCh : numChambre) {
            Chambre chambre = chambreRepository.findByNumeroChambre(numCh);
            chambre.setBloc(bloc);
            chambreRepository.save(chambre);
        }
        return bloc;
    }

    //V2
    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).get();

        for (Long numCh : numChambre) {
            Chambre chambre = chambreRepository.findByNumeroChambre(numCh);
            chambre.setBloc(bloc);
            chambreRepository.save(chambre);
        }
        return bloc;
    }


}
