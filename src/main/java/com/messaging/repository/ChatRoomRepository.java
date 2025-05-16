package com.messaging.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.messaging.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID>{

      // @Query("SELECT cr FROM ChatRoom cr " +
      //       "JOIN cr.participants p " +
      //       "WHERE p.user.username IN :participants " +
      //       "GROUP BY cr.id " +
      //       "HAVING COUNT(CASE WHEN p.user.username IN :participants THEN 1 END) = :participantCount" +
      //                   "AND COUNT(DISTINCT p.user.username) = :participantCount")
      // ChatRoom findChatRoomByParticipants(@Param("participants") List<String> participants,
      //                               @Param("participantCount") long participantCount);

      @Query("SELECT cr FROM ChatRoom cr " +
                  "JOIN cr.participants p " +
                  "WHERE p.user.username IN :participants " +
                  "GROUP BY cr.id " +
                  "HAVING COUNT(CASE WHEN p.user.username IN :participants THEN 1 END) = :participantCount " +
                        "AND COUNT(DISTINCT p.user.username) = :participantCount")
      ChatRoom findChatRoomByParticipants(@Param("participants") List<String> participants,
                                    @Param("participantCount") long participantCount);

      List<ChatRoom> findByCreatedByUsername(String username);

      @Query("SELECT cr FROM ChatRoom cr " +
                  "JOIN cr.participants p " +
                  "WHERE p.user.username = :username")
      List<ChatRoom> findChatRoomsByParticipant(@Param("username") String username);

      @Query("SELECT cr FROM ChatRoom cr " +
                  "WHERE cr.isGroup = true")
      List<ChatRoom> findAllGroupChatRooms();

      @Query("SELECT cr FROM ChatRoom cr " +
                  "WHERE cr.isGroup = false")
      List<ChatRoom> findAllPrivateChatRooms();

      @Query("SELECT cr FROM chatRoom cr " +
            "JOIN cr.messages m " +
            "JOIN cr.participants p " +
            "WHERE p.user.username = :username " +
            "GROUP BY cr.id " +
            "HAVING COUNT(m.content) > 0 " +
            "ORDER BY MAX(m.dateSentMessage) DESC")
      List<ChatRoom> findMessageAtLeastOneContent(@Param("username") String username);

}
