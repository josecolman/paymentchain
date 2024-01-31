/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biagab.product.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The effort to standardize rest API error reports  is support by
 * ITEF (Internet Engineering Task Force, open standard organization  that which develop and promotes voluntary internet standards)
 in RFC 7807 which created a generalized error-handling schema composed by five parts.
1- type — A URI identifier that categorizes the error
2- title — A brief, human-readable message about the error
3- code —  The unique error code
4- detail — A human-readable explanation of the error
5- instance — A URI that identifies the specific occurrence of the error
 Standardized is optional but have advantage, it is use for facebook and Twitter ie
 * https://graph.facebook.com/oauth/access_token?
 * https://api.twitter.com/1.1/statuses/update.json?include_entities=true
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardizedApiExceptionResponse {

    @Schema(
            name = "type",
            example = "/errors/authentication/not-authorized",
            $comment = "The unique uri identifier that categorizes the error"
    )
    private String type ="/errors/uncategorized";

    @Schema(
            name = "title",
            example = "The user does not have authorization",
            $comment = "A brief, human-readable message about the error"
    )
    private String title;

    @Schema(
            name = "code",
            example = "192",
            $comment = "The unique error code"
    )
    private String code;

    @Schema(
            name = "detail",
            example = "The user does not have the properly permissions to access the resource, " +
                    "please contact with ass https://digitalthinking.biz/es/ada-enterprise-core#contactus",
            $comment = "A human-readable explanation of the error"
    )
    private String detail;

     /*  @ApiModelProperty(notes = "A URI that identifies the specific occurrence of the error", name = "detail",
           required = true, example = "/errors/authentication/not-authorized/01")*/
    @Schema(
            name = "instance",
            example = "/errors/authentication/not-authorized/01",
            $comment = "A URI that identifies the specific occurrence of the error"
    )
    private String instance ="/errors/uncategorized/bank";

}
