package src;


import src.building.Building;
import src.building.Premise;
import src.building.Room;
import src.documentStorage.*;

public class Main {
    public static void main(String[] args) {
//        Author authorPetrov = new Author("Petrov");
//        Book book1 = new Book();
//
//
//        System.out.println(authorPetrov);
//        System.out.println(book1);
//
//
//        Document doc1 = new Document(authorPetrov, 2, "txt", "Document 1" , "5669ff" );
//
//        Novel novel1 = new Novel(authorPetrov, "Abstract Novel 1", 2, "txt", "First Novel", "152DD");
//        DocumentStorage repo = new DocumentStorage(100, 0, 0);
//        repo.storeDocument(book1);
//        System.out.println(repo);

        Building building = new Building(200);
        Premise premise = new Premise(60, building);
        Room room = new Room(30, building, 3);

        System.out.println(building);
        System.out.println(premise);

        building.capacity();
        premise.capacity();
        room.capacity();

    }
}
