package edu.kit.informatik.structures;

import java.util.HashSet;
import java.util.Set;

public abstract class Entity implements Identifiable {
    
    private Set<String> keywords;
    
    /**
     * Entites are objects that have keywords that describe
     * their properties.
     */
    public Entity() {
        keywords = new HashSet<>();
    }
    
    /**
     * List of all keywords.
     * Returns an empty list if it has no keywords.
     * 
     * @return set of keywords
     */
    public Set<String> getKeywords() {
        return keywords;
    }
    
    /**
     * Adds a keyword to this entity.
     * 
     * @param keyword to add
     */
    public void addKeyword(String keyword) {
        keywords.add(keyword);
    }
    
    @Override
    public String toString() {
        return getId();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entity) {
            Entity anotherEntity = (Entity) obj;
            return this.getId().equals(anotherEntity.getId());
        }
        return false;
    }
}
