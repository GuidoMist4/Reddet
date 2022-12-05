package net.bcsoft.com.Reddet.mapper;

import net.bcsoft.com.Reddet.DTO.SubReddetDTO;
import net.bcsoft.com.Reddet.model.Post;
import net.bcsoft.com.Reddet.model.SubReddet;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper (componentModel = "spring")
public interface SubReddetMapper {

    @Mapping(target = "postNumber", expression = "java(mapPosts(subReddet.getPosts()))")
    SubReddetDTO mapSubReddetToDTO(SubReddet subReddet);

    default Integer mapPosts(List<Post> postNumber){
        return postNumber.size();
    }


    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    SubReddet mapDTOToSubReddet(SubReddetDTO subReddetDTO);
}
