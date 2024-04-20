package src;


import src.documentStorage.*;

public class Main {
    public static void main(String[] args) {
        Author authorPetrov = new Author("Petrov");
        Book book1 = new Book();


        System.out.println(authorPetrov);
        System.out.println(book1);


        Document doc1 = new Document(authorPetrov, 2, "txt", "Document 1" , "5669ff" );

        Novel novel1 = new Novel(authorPetrov, "Abstract Novel 1", 2, "txt", "First Novel", "152DD");
        DocumentStorage repo = new DocumentStorage(100, 0, 0);
        repo.storeDocument(book1);
        System.out.println(repo);
    }
}
