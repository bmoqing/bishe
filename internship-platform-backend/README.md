# 高校学生实习管理平台 - Spring Boot 后端 (MVP)

## 启动步骤
1. 安装 JDK 17、Maven、MySQL 8+
2. 创建数据库：`create database internship default charset utf8mb4;`
3. 修改 `src/main/resources/application.yml` 中的数据库账号密码；如需调整 JWT 密钥与权重也可修改。
4. 先准备基础角色和一个管理员账号（见下方 SQL），然后启动：
   ```bash
   mvn spring-boot:run
   ```

## 初始化 SQL
```sql
INSERT INTO roles(code, name) VALUES ('ADMIN','管理员'),('STUDENT','学生'),('TEACHER','教师'),('COMPANY','企业');
-- admin123 的常见 BCrypt 示例（如不生效，可自行使用 BCrypt 生成再更新）
INSERT INTO users(username,password,realName) VALUES ('admin','$2a$10$7EqJtq98hPqEX7fNZaFWoOJu1u7d2Z8g8PikS5VB5EXkNKOQ0Z5e.','超管');
INSERT INTO user_roles(user_id, role_id) VALUES (1, (SELECT id FROM roles WHERE code='ADMIN'));
```

> 提醒：如果你的环境 BCrypt 版本不同，建议通过注册接口创建用户更稳妥。

## 主要接口（节选）
- 认证：
  - `POST /api/auth/login` {username,password} -> {token}
  - `POST /api/auth/register` {username,password,realName,role}
- 实习申请：
  - `POST /api/internship/apply` (学生) -> 创建申请
  - `POST /api/internship/{id}/teacher-approve?pass=true&teacherUserId=...&teacherName=...`
  - `POST /api/internship/{id}/admin-record?pass=true&adminUserId=...&adminName=...`
- 日志：
  - `POST /api/log/submit`
  - `GET /api/log/my/{studentId}`
- 考勤：
  - `POST /api/attendance/check-in` 传入 {studentId,lat,lng,ip,companyId}
- 评价与成绩：
  - `POST /api/eval/company` / `POST /api/eval/teacher`
  - `POST /api/grade/compute`
- 报表导出：
  - `GET /api/report/grades.xlsx` / `GET /api/report/grades.pdf`

## 说明
- 为简化演示，部分查询使用内存过滤，正式项目请用 Repository 自定义查询。
- 前端建议：Vue3 + Vite + Element Plus / ECharts。拦截器在请求头加 `Authorization: Bearer <token>`。
