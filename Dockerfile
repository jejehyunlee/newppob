# Menggunakan base image OpenJDK
FROM openjdk:17-jdk-alpine

# Menambahkan metadata untuk dokumentasi (opsional)
LABEL maintainer="jefrisaputra989@gamil.com"
LABEL version="1.0"
LABEL description="Aplikasi Spring Boot dengan Docker"

# Menentukan direktori kerja di dalam container
WORKDIR /app

# Menyalin file JAR dari sistem lokal ke container
COPY target/your-application.jar app.jar

# Menentukan port aplikasi yang akan diekspos
EXPOSE 8080

# Menjalankan aplikasi Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]

