package al.ikubinfo.internship.freelancer.mapper;

import java.util.ArrayList;
import java.util.List;

public interface Mapper<E, M> {
    E toEntity(M model);

    M toModel(E entity);
    
    default List<E> toEntityList(List<M> modelList){
    	List<E> entityList = new ArrayList<>();
    	for(M model: modelList) {
    		entityList.add(this.toEntity(model));
    	}
    	return entityList;
    }
    
    default List<M> toModelList(List<E> entityList){
    	List<M> modelList = new ArrayList<>();
    	for(E entity: entityList) {
    		modelList.add(this.toModel(entity));
    	}
    	return modelList;
    }
}
