package ru.itis.zadachnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zadachnik.models.Group;
import ru.itis.zadachnik.repositories.GroupsRepository;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupsRepository groupsRepository;

    public List<Group> getAllGroups(){
        return groupsRepository.findAll();
    }
}
