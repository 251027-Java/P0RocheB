package com.example;

import java.sql.SQLException;

import com.example.repository.PostgreSQLRepository;
import com.example.service.Service;

public class Main {
    public static void main(String[] args) throws SQLException{
        PostgreSQLRepository repo = new PostgreSQLRepository();
        Service service = new Service(repo);
        /*
        Game game = new Game(1, "[Event \"Polerio\"]\n" + //
                        "[Site \"Rome\"]\n" + //
                        "[Date \"1610.??.??\"]\n" + //
                        "[Round \"?\"]\n" + //
                        "[White \"Polerio, Giulio Cesare\"]\n" + //
                        "[Black \"D'Arminio, Domenico\"]\n" + //
                        "[Result \"1-0\"]\n" + //
                        "[ECO \"C57\"]\n" + //
                        "[Annotator \"Kahn,V\"]\n" + //
                        "[PlyCount \"47\"]\n" + //
                        "[EventDate \"1610.??.??\"]\n" + //
                        "[EventType \"game\"]\n" + //
                        "[EventCountry \"ITA\"]\n" + //
                        "[SourceTitle \"EXT 1999\"]\n" + //
                        "[Source \"ChessBase\"]\n" + //
                        "[SourceDate \"1998.11.16\"]\n" + //
                        "[SourceVersion \"1\"]\n" + //
                        "[SourceVersionDate \"1998.11.16\"]\n" + //
                        "[SourceQuality \"1\"]\n" + //
                        "\n" + //
                        "1. e4 {La plus ancienne partie connue après la réforme du jeu des échecs date\n" + //
                        "de 1485. jouée entre Francisco De CALTEVI et Narcisco VIGNOLES, cette partie\n" + //
                        "qu'on trouve dans \"the golden Treasury of chess\", New-York de F.J WELLMUTH est\n" + //
                        "assez faible. Rien d'étonnant, les échecs modernes venaient seulement de\n" + //
                        "naître. Mais le même ouvrage contient une partie que le génial POLERIO joua en\n" + //
                        "1580. Nous y voyons au 06ème coup, ce sacrifice qu'EUWE, entre autres\n" + //
                        "commentateurs attribua à GRECO.  Le \"Fegatello\" aurait donc été pratiqué 20\n" + //
                        "ans avant sa naissance présumée.} e5 2. Nf3 Nc6 3. Bc4 Nf6 4. Ng5 d5 5. exd5\n" + //
                        "Nxd5 {Nous ne mettons pas de point d'interrogation usuel à ce coup car les\n" + //
                        "analyses n'en donnent pas de réfutation pratique. Si on ne voit guère ce coup\n" + //
                        "en pratique, c'est pour des raisons psychologiques. Les noirs craignent le\n" + //
                        "désagréable exode du N} 6. Nxf7 $1 {Ce point d'exclamation ne signifie pas non\n" + //
                        "plus que le sacrifice est la continuation la plus forte des blancs mais nous\n" + //
                        "tenons à saluer le génie de POLERIO, inventeur du Fegatello.} (6. d4 {Les\n" + //
                        "analyses récentes préfèrent 6.d4}) 6... Kxf7 7. Qf3+ Ke6 8. Nc3 Nce7 (8... Ncb4\n" + //
                        "{Déjà STEINITZ avait renforcé avec Nb4} 9. Qe4 (9. a3 Nxc2+ 10. Kd1 Nd4 11.\n" + //
                        "Bxd5+ Kd6 $11) 9... c6 10. a3 Na6 11. d4 Nac7 $1 12. Bf4 (12. Qxe5+ Kf7) 12...\n" + //
                        "Kf7 13. Bxe5 {Et comme dit EUWE, il faut encore prouver que l'attaque blanche\n" + //
                        "vaut la pièce sacrifiée}) 9. d4 $1 c6 10. Bg5 h6 11. Bxe7 Bxe7 12. O-O-O Rf8\n" + //
                        "13. Qe4 Rxf2 14. dxe5 Bg5+ 15. Kb1 Rd2 16. h4 $1 Rxd1+ 17. Rxd1 Bxh4 18. Nxd5\n" + //
                        "cxd5 19. Rxd5 Qg5 20. Rd6+ Ke7 21. Rg6 $1 Qd2 22. Rxg7+ Kf8 23. Rg8+ Ke7 24.\n" + //
                        "Qh7# 1-0");
        
        repo.createGame(game);
        */
        System.out.println(repo.readGame(1));
        System.out.println(repo.readGame(2));
        System.out.println(repo.readGame(3));
    }
}