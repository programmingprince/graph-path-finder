package graph;

import java.util.List;

public class Vertex {
    final private String id;
    final private String name;
    private boolean visited;
    private List<String> interests;

    public Vertex(String id, String name, List<String> interests) {
        this.id = id;
        this.name = name;
        this.visited = false;
        this.interests = interests;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public List<String> getInterests() {
        return this.interests;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}