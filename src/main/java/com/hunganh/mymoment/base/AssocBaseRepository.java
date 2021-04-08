package com.hunganh.mymoment.base;

import com.hunganh.mymoment.model.assoc.VerificationOwnership;
import com.hunganh.mymoment.model.object.User;
import com.hunganh.mymoment.model.object.VerificationToken;
import com.hunganh.mymoment.repository.ProfileRepository;
import com.hunganh.mymoment.repository.UserRepository;
import com.hunganh.mymoment.repository.VerificationTokenRepository;
import com.hunganh.mymoment.repository.assoc.VerificationOwnershipRepository;
import com.sn.appbase.constant.SnwAssocType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashSet;

import static com.sn.appbase.constant.SnwAssocType.*;

/**
 * @Author: Tran Tuan Anh
 * @Created: Thu, 01/04/2021 11:29 PM
 **/

@AllArgsConstructor
@Repository
public class AssocBaseRepository {
    private final VerificationOwnershipRepository verificationOwnershipRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    public void addAssoc(Object startNode, SnwAssocType snwAssocType, Object endNode, String data, long time){
        switch (snwAssocType){
            case HAS_POST:

                break;
            case HAS_VERTIFICATION_TOKEN:{
                if ((startNode instanceof User) && (endNode instanceof VerificationToken)){
                    //cast object
                    User user = (User) startNode;
                    VerificationToken verificationToken = (VerificationToken) endNode;
                    //create ownership
                    VerificationOwnership verificationOwnership =
                            VerificationOwnership.builder()
                                    .startNode(user)
                                    .endNode(verificationToken)
                                    .time(time)
                                    .build();
                    //add relationship for start node
                    if (user.getVerificationOwnerships()==null){
                        user.setVerificationOwnerships(new HashSet<>());
                    }
                    user.getVerificationOwnerships().add(verificationOwnership);
                    userRepository.save(user);
                    //add relationship for end node
                    if (verificationToken.getVerificationOwnerships()==null){
                        verificationToken.setVerificationOwnerships(new HashSet<>());
                    }
                    verificationToken.getVerificationOwnerships().add(verificationOwnership);
                    verificationTokenRepository.save(verificationToken);
                }
                break;
            }

            case HAS_COMMENT:
            case HAS_REACTION:
            case HAS_TAG:
            case HAS_HASHTAG:
            case FOLLOWS:
            default:
                throw new IllegalStateException("Unexpected value: ");
        }
    }
    public void deleteAssoc(Object startNode, SnwAssocType snwAssocType, Object endNode){
        switch (snwAssocType){
            case HAS_POST:

                break;
            case HAS_VERTIFICATION_TOKEN:{
//                if ((startNode instanceof User) && (endNode instanceof VerificationToken)){
//                    //cast object
//                    User user = (User) startNode;
//                    VerificationToken verificationToken = (VerificationToken) endNode;
//                    //create ownership
//                    VerificationOwnership verificationOwnership =
//                            VerificationOwnership.builder()
//                                    .startNode(user)
//                                    .endNode(verificationToken)
//                                    .time(0)
//                                    .build();
//                    //add relationship for start node
//                    if (user.getVerificationOwnerships()==null){
//                        user.setVerificationOwnerships(new HashSet<>());
//                    }
//                    user.getVerificationOwnerships().add(verificationOwnership);
//                    userRepository.save(user);
//                    //add relationship for end node
//                    if (verificationToken.getVerificationOwnerships()==null){
//                        verificationToken.setVerificationOwnerships(new HashSet<>());
//                    }
//                    verificationToken.getVerificationOwnerships().add(verificationOwnership);
//                    verificationTokenRepository.save(verificationToken);
//                }
                break;
            }

            case HAS_COMMENT:
            case HAS_REACTION:
            case HAS_TAG:
            case HAS_HASHTAG:
            case FOLLOWS:
            default:
                throw new IllegalStateException("Unexpected value: ");
        }
    }
}
