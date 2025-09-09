package com.nesstiff.reference.metadataservice.service.impl;

import com.nesstiff.grpc.BankServiceGrpc;
import com.nesstiff.grpc.GrpcBankIdRequest;
import com.nesstiff.grpc.GrpcBankResponse;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcBankServiceImpl extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getById(GrpcBankIdRequest request, StreamObserver<GrpcBankResponse> responseObserver) {
        try {

//            List<UserStaff> userStaffList = userStaffService.findAllByKeyCloakIdIn(keycloakIds);
//
//            List<UserStaffDto> dtos = userStaffList.stream()
//                    .filter(userStaff -> userStaff.getUser() != null)
//                    .map(this::map)
//                    .toList();

            GrpcBankResponse response = GrpcBankResponse.newBuilder()
                    .setBankId(10)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(
                    Status.INTERNAL.withDescription("Unexpected error occurred: ".concat(e.getMessage() != null ? e.getMessage() : "" ))));
        }
    }
}
