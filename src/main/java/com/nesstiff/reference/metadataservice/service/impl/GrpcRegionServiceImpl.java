package com.nesstiff.reference.metadataservice.service.impl;

import com.nesstiff.grpc.GrpcCountryNameRegionRequest;
import com.nesstiff.grpc.GrpcIdRegionRequest;
import com.nesstiff.grpc.GrpcRegionResponse;
import com.nesstiff.grpc.RegionServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcRegionServiceImpl extends RegionServiceGrpc.RegionServiceImplBase {

    @Override
    public void getById(GrpcIdRegionRequest request, StreamObserver<GrpcRegionResponse> responseObserver) {
        try {


            GrpcRegionResponse response = GrpcRegionResponse.newBuilder()
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(
                    Status.INTERNAL.withDescription("Unexpected error occurred: ".concat(e.getMessage() != null ? e.getMessage() : ""))));
        }
    }

    @Override
    public void getCountryByName(GrpcCountryNameRegionRequest request, StreamObserver<GrpcRegionResponse> responseObserver) {
        try {


            GrpcRegionResponse response = GrpcRegionResponse.newBuilder()
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new StatusRuntimeException(
                    Status.INTERNAL.withDescription("Unexpected error occurred: ".concat(e.getMessage() != null ? e.getMessage() : ""))));
        }
    }
}
