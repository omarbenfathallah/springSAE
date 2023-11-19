package com.example.omar.Service;

import com.example.omar.Entity.Bloc;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BlocService {
     Bloc createBloc(Bloc bloc);
     List<Bloc> getAllBloc();
     Optional<Bloc> getBlocById(long id);
     Bloc updateBloc(Bloc bloc,long id);
     void deleteBloc(long id);
     Bloc afffecterChambresABloc (List<Long> numChambre, String nomBloc);

     Bloc affecterChambresABloc(List<Long> numChambre, long idBloc);
}

