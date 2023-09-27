package fr.ecolnum.projectapi.DTO;

import fr.ecolnum.projectapi.model.Criteria;
import fr.ecolnum.projectapi.model.Observer;
import fr.ecolnum.projectapi.model.Pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * DTO is a pattern (data transfert object) which use for the recursivity
 * DTO take only the id for the pools which associated no more
 */
public class CriteriaDto {
    private int id;
    private String name;
    private String description;
    /**
     * create an object for the list which are integers
     */
    private List<Integer> existInPool;
    public CriteriaDto() {
    }

    /**
     *
     * @param criteria this construct take attributs of criteria
     */
    public CriteriaDto(Criteria criteria) {
        this.id = criteria.getId();
        this.name = criteria.getName();
        this.description = criteria.getDescription();
        /**
         * transform the pool list in object existsIn which contain an ArrayList
         */
        Set<Pool> existsIn = criteria.getExistsIn();
        existInPool = new ArrayList<>();
        /**
         * just take all id contain in pool List
         */
        for (Pool poolList : existsIn) {
            existInPool.add(poolList.getId());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getExistInPool() {
        return existInPool;
    }

    public void setExistInPool(List<Integer> existInPool) {
        this.existInPool = existInPool;
    }
}
