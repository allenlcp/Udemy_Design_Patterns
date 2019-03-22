package e002_challenge;

public interface Visitor {
    void visit(Book book);
    void visit(CD cd);
    void visit(DVD dvd);
    double getTotalPostage();
}

class USPostageVisitor implements Visitor{
    private double totalPostageForCart = 0;

    @Override
    public void visit(Book book) {
        if(book.getPrice() < 20) {
            totalPostageForCart += book.getWeight() * 2;
        }
    }

    @Override
    public void visit(CD cd) {
        if(cd.getPrice() < 20) {
            totalPostageForCart += cd.getWeight() * 2.5;
        }
    }

    @Override
    public void visit(DVD dvd) {
        if(dvd.getPrice() < 20) {
            totalPostageForCart += dvd.getWeight() * 3;
        }
    }

    public double getTotalPostage(){
        return totalPostageForCart;
    }
}


class SouthAmericaSPostageVisitor implements Visitor{
    private double totalPostageForCart = 0;

    @Override
    public void visit(Book book) {
        if(book.getPrice() < 30) {
            totalPostageForCart += (book.getWeight() * 2) * 2;
        }
    }

    @Override
    public void visit(CD cd) {
        if(cd.getPrice() < 30) {
            totalPostageForCart += (cd.getWeight() * 2.5) * 2;
        }
    }

    @Override
    public void visit(DVD dvd) {
        if(dvd.getPrice() < 30) {
            totalPostageForCart += (dvd.getWeight() * 3) * 2;
        }
    }

    public double getTotalPostage(){
        return totalPostageForCart;
    }
}
