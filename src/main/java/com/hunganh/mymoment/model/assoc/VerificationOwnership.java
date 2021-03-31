package com.hunganh.mymoment.model.assoc;

import com.hunganh.mymoment.model.object.User;
import com.hunganh.mymoment.model.object.VerificationToken;
import com.sn.appbase.model.SnwAssoc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * @Author: Tran Tuan Anh
 * @Created: Thu, 18/03/2021 11:54 PM
 **/

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@RelationshipEntity(type = "HAS_VERIFICATION_TOKEN")
public class VerificationOwnership extends SnwAssoc<User, VerificationToken> {
}
