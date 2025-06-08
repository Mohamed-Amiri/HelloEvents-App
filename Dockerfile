# استعمل صورة OpenJDK 23 كبيئة تنفيذ
FROM openjdk:23-jdk-slim

# حدد مجلد العمل داخل الحاوية
WORKDIR /app

# نسخ ملف jar من المشروع للمجلد /app داخل الحاوية
COPY target/HelloEvent-0.0.1-SNAPSHOT.jar app.jar


# أمر تشغيل التطبيق
ENTRYPOINT ["java", "-jar", "app.jar"]

# تحديد البورت اللي التطبيق خدام عليه (8081 في حالتك)
EXPOSE 8081
