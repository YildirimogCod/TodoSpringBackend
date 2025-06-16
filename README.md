# Spring Boot TODO List API

Spring Boot ile geliştirilmiş RESTful TODO yönetim API'si. Bu proje, Spring Boot'un auto-configuration özelliklerini ve modern REST API geliştirme best practice'lerini göstermektedir.

## 🚀 Özellikler

- ✅ CRUD operasyonları (Create, Read, Update, Delete)
- ✅ Durum bazlı filtreleme (tamamlanan/bekleyen)
- ✅ Partial update desteği
- ✅ DTO pattern ile temiz mimari
- ✅ Bean validation ile veri doğrulama
- ✅ İstatistik endpoint'leri
- ✅ Bulk operasyonlar
- ✅ CORS desteği
- ✅ RESTful API tasarımı

## 📋 Teknolojiler

- **Java 11**
- **Spring Boot 2.7.12**
- **Spring Web MVC**
- **Bean Validation**
- **Maven**

## 🏗️ Proje Yapısı

```
todo-api/
├── src/main/java/com/example/
│   ├── TodoApiApplication.java          # Ana uygulama sınıfı
│   ├── controller/
│   │   └── TodoController.java          # REST endpoint'leri
│   ├── service/
│   │   └── TodoService.java             # İş mantığı
│   ├── model/
│   │   └── Todo.java                    # Entity model
│   └── dto/
│       ├── TodoRequest.java             # Input DTO
│       ├── TodoResponse.java            # Output DTO
│       └── TodoUpdateRequest.java       # Partial update DTO
├── src/main/resources/
│   └── application.yml                  # Konfigürasyon
└── pom.xml                             # Maven bağımlılıkları
```

## 🛠️ Kurulum

### Gereksinimler
- Java 11 veya üzeri
- Maven 3.6+

### Projeyi Çalıştırma

1. **Repoyu klonlayın:**
   ```bash
   git clone <repository-url>
   cd todo-api
   ```

2. **Bağımlılıkları yükleyin:**
   ```bash
   mvn clean install
   ```

3. **Uygulamayı başlatın:**
   ```bash
   mvn spring-boot:run
   ```

4. **API'ye erişin:**
   ```
   http://localhost:8080/api
   ```

## 📚 API Endpoints

### TODO İşlemleri

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| `GET` | `/api/todos` | Tüm TODO'ları listele |
| `GET` | `/api/todos?completed=true` | Tamamlanan TODO'ları listele |
| `GET` | `/api/todos?completed=false` | Bekleyen TODO'ları listele |
| `GET` | `/api/todos/{id}` | Belirli TODO'yu getir |
| `POST` | `/api/todos` | Yeni TODO oluştur |
| `PUT` | `/api/todos/{id}` | TODO güncelle |
| `PATCH` | `/api/todos/{id}/toggle` | TODO durumunu değiştir |
| `DELETE` | `/api/todos/{id}` | TODO sil |

### İstatistik ve Bulk İşlemler

| Method | Endpoint | Açıklama |
|--------|----------|----------|
| `GET` | `/api/todos/stats` | TODO istatistikleri |
| `POST` | `/api/todos/bulk` | Toplu TODO oluştur |
| `PATCH` | `/api/todos/mark-all-completed` | Tüm TODO'ları tamamla |

## 📖 Kullanım Örnekleri

### Yeni TODO Oluşturma
```bash
POST http://localhost:8080/api/todos
Content-Type: application/json

{
    "title": "Spring Security öğren",
    "description": "JWT ve OAuth2 konularını incele"
}
```

**Yanıt:**
```json
{
    "id": 1,
    "title": "Spring Security öğren",
    "description": "JWT ve OAuth2 konularını incele",
    "completed": false,
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
}
```

### TODO Güncelleme (Partial Update)
```bash
PUT http://localhost:8080/api/todos/1
Content-Type: application/json

{
    "title": "Spring Boot PRO seviye",
    "completed": true
}
```

### Durum Değiştirme
```bash
PATCH http://localhost:8080/api/todos/1/toggle
```

### İstatistik Görüntüleme
```bash
GET http://localhost:8080/api/todos/stats
```

**Yanıt:**
```json
{
    "total": 5,
    "completed": 2,
    "pending": 3
}
```

## 🏛️ Mimari Özellikleri

### DTO Pattern
Proje, güvenli ve temiz API tasarımı için DTO (Data Transfer Object) pattern'ini kullanır:

- **TodoRequest**: Client'tan gelen veri için
- **TodoResponse**: Client'a dönen veri için  
- **TodoUpdateRequest**: Partial update işlemleri için

### Validation
Bean validation ile otomatik veri doğrulama:

```java
@NotBlank(message = "Başlık zorunludur")
@Size(max = 100, message = "Başlık 100 karakterden uzun olamaz")
private String title;
```

### Error Handling
Spring Boot'un otomatik error handling mekanizması kullanılır.

## ⚙️ Konfigürasyon

### application.yml
```yaml
server:
  port: 8080
  servlet:
    context-path: /api

spring:
  application:
    name: todo-api

logging:
  level:
    com.example: DEBUG
```

## 🧪 Test Etme

### Postman Collection
API'yi test etmek için aşağıdaki endpoint'leri kullanabilirsiniz:

1. **Tüm TODO'ları getir:** `GET /api/todos`
2. **Yeni TODO oluştur:** `POST /api/todos`
3. **TODO güncelle:** `PUT /api/todos/{id}`
4. **TODO sil:** `DELETE /api/todos/{id}`

### cURL Örnekleri

```bash
# Tüm TODO'ları listele
curl -X GET http://localhost:8080/api/todos

# Yeni TODO oluştur
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"Test TODO","description":"Test açıklama"}'

# TODO durumunu değiştir
curl -X PATCH http://localhost:8080/api/todos/1/toggle
```

---

**Spring Boot'un Gücü:** Bu proje, Spring Boot'un auto-configuration, starter dependencies ve convention-over-configuration prensipleriyle nasıl hızlı ve etkili API geliştirilebildiğini göstermektedir.
