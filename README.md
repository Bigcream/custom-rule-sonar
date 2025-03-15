Để giá trị này áp dụng sau khi khởi động lại máy, chỉnh sửa file cấu hình hệ thống:

Mở file /etc/sysctl.conf:

```sudo nano /etc/sysctl.conf```

Thêm hoặc sửa dòng sau ở cuối file:

```vm.max_map_count=262144```

Áp dụng thay đổi:

```sudo sysctl -p```

Kiếm tra mount sona qube:

```docker volume ls```

Kiểm tra đường dẫn mount trên ubuntu:

```docker volume inspect <project_name>_sonarqube_extensions```

Kiểm tra plugins đang có:

```sudo ls -la /var/lib/docker/volumes/thang95tx_sonarqube_extensions/_data/plugins```

Copy plugin trên máy vào sonaqube plugins:

```sudo cp ./target/sonar-custom-rules-1.0-SNAPSHOT.jar /var/lib/docker/volumes/thang95tx_sonarqube_extensions/_data/plugins```

Xóa plugin trong folder sonaqube plugins:
```sudo rm /var/lib/docker/volumes/thang95tx_sonarqube_extensions/_data/plugins/sonar-custom-rules-1.0-SNAPSHOT.jar```
