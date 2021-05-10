FROM public.ecr.aws/lambda/provided:al2
RUN yum -y install java-11-amazon-corretto-headless
COPY src/shell/java/bootstrap ${LAMBDA_RUNTIME_DIR}
# Copy function code and runtime dependencies from Gradle layout
ADD target/spring-native-function-aws-dynamodb-kotlin-1.0.0-java.tar.gz ${LAMBDA_TASK_ROOT}/

# Set the CMD to your handler (could also be done as a parameter override outside of the Dockerfile)
CMD [ "uppercase" ]