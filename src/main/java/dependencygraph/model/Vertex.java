package dependencygraph.model;

/**
 * Model for representing a vertex for a graph
 *
 * @author Jijo Wilson (jiwilson@expedia.com)
 */
public class Vertex {

    public Vertex(String name) {
        // TODO: Can use generics here
        this.name = name;
    }

    private String name;


    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (o instanceof Vertex) {
            Vertex input = (Vertex) o;
            isEqual = input.getName().equals(this.getName());

        }
        return isEqual;
    }
}
