package peaksoft.springbootlesson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.springbootlesson.dto.*;
import peaksoft.springbootlesson.entity.Company;
import peaksoft.springbootlesson.entity.Course;
import peaksoft.springbootlesson.entity.Group;
import peaksoft.springbootlesson.repository.GroupRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    public List<GroupResponse> getAllGroups(){
        List<GroupResponse> groupResponses = new ArrayList<>();
        for (Group group : groupRepository.findAll()) {
            groupResponses.add(mapToResponse(group));
        }
        return groupResponses;
    }
    public GroupResponse getGroupById(Long groupId){
        Group group = groupRepository.findById(groupId).get();
        return mapToResponse(group);
    }
    public GroupResponse create(GroupRequest request){
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        groupRepository.save(group);
        return mapToResponse(group);
    }
    public GroupResponse updateGroup(Long id,GroupRequest request){
        Group group = groupRepository.findById(id).get();
        group.setGroupName(request.getGroupName());
        group.setDateOfStart(request.getDateOfStart());
        group.setDateOfFinish(request.getDateOfFinish());
        group.setStudents(group.getStudents());
        groupRepository.save(group);
        return mapToResponse(group);
    }
    public void deleteGroup(Long groupId){
        groupRepository.deleteById(groupId);
    }


    public GroupResponse mapToResponse(Group group){
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setGroupName(group.getGroupName());
        groupResponse.setDateOfStart(group.getDateOfStart());
        groupResponse.setDateOfFinish(group.getDateOfFinish());
        return groupResponse;
    }

}
