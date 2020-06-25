class Cat {

    // write static and instance variables
    static int counter = 0;
    int age;
    String name;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        counter++;

        if (counter > 5) {
            System.out.println("You have too many cats");
        }
        
        
    }

    public static int getNumberOfCats() {
        return counter;
    }
}
