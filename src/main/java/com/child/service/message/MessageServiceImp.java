package com.child.service.message;

import com.child.dao.message.MessageDao;
import com.child.dto.MessageDto;
import com.child.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageServiceImp implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public Message createMessage(MessageDto messageDto) {
        String fname = messageDto.getFname();
        String lname = messageDto.getLname();
        String email = messageDto.getEmail();
        String messages = messageDto.getMessage();

        Message message = new Message();
        message.setFname(fname);
        message.setLname(lname);
        message.setEmail(email);
        message.setMessage(messages );

        return messageDao.create(message);
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageDao.findById(id);
    }
}
