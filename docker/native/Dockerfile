FROM public.ecr.aws/lambda/provided:al2
COPY src/shell/native/bootstrap ${LAMBDA_RUNTIME_DIR}
# Copy function code and runtime dependencies from Gradle layout
ADD target/spring-native-function-aws-dynamodb-kotlin-1.0.0-SNAPSHOT-native.tar.gz ${LAMBDA_TASK_ROOT}/
EXPOSE 8080
# Set the CMD to your handler (could also be done as a parameter override outside of the Dockerfile)
CMD [ "uppercase" ]