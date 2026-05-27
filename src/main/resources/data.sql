-- 插入管理员账号 (用户名: admin, 密码: admin123)
INSERT INTO t_user (username, password, real_name, phone, email, gender, department, student_no, role, create_time)
SELECT 'admin', 'admin123', '系统管理员', '13800000000', 'admin@campus.com', '男', '信息中心', 'ADMIN001', 2, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'admin');

-- 插入普通测试用户 (用户名: zhangsan, 密码: 123456)
INSERT INTO t_user (username, password, real_name, phone, email, gender, department, student_no, role, create_time)
SELECT 'zhangsan', '123456', '张三', '13800000001', 'zhangsan@campus.com', '男', '计算机学院', '2024001', 0, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'zhangsan');


-- 插入示例社团
INSERT INTO t_club (name, category, description, president, contact_phone, member_count, status, creator_id, create_time)
SELECT '计算机协会', '学术科技', '计算机协会致力于推广计算机知识，组织编程竞赛和技术分享活动，帮助同学们提升技术能力。', '张三', '13800000001', 5, 1, 1, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_club WHERE name = '计算机协会');

INSERT INTO t_club (name, category, description, president, contact_phone, member_count, status, creator_id, create_time)
SELECT '文学社', '文化艺术', '文学社是一个热爱文学创作的社团，定期举办诗歌朗诵、读书分享会和文学创作比赛。', '李四', '13800000002', 3, 1, 1, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_club WHERE name = '文学社');

INSERT INTO t_club (name, category, description, president, contact_phone, member_count, status, creator_id, create_time)
SELECT '篮球社', '体育运动', '篮球社汇聚了校园篮球爱好者，每周定期训练并参加各类校际篮球赛事。', '王五', '13800000003', 3, 1, 1, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_club WHERE name = '篮球社');

INSERT INTO t_club (name, category, description, president, contact_phone, member_count, status, creator_id, create_time)
SELECT '志愿者协会', '志愿公益', '志愿者协会组织各类公益活动，包括社区服务、环保宣传和爱心支教等。', '赵六', '13800000004', 4, 1, 1, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_club WHERE name = '志愿者协会');

-- 插入示例活动
INSERT INTO t_activity (title, content, location, start_time, end_time, club_id, club_name, publisher_id, publisher_name, max_participants, current_participants, status, create_time)
SELECT '2026年春季编程马拉松', '面向全校同学的编程马拉松活动，以"智慧校园"为主题，参赛选手需在48小时内完成项目开发。优秀作品将获得丰厚奖品！', '图书馆多功能厅', DATE_ADD(NOW(), INTERVAL 7 DAY), DATE_ADD(NOW(), INTERVAL 9 DAY), 1, '计算机协会', 1, 'admin', 50, 12, 0, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_activity WHERE title = '2026年春季编程马拉松');

INSERT INTO t_activity (title, content, location, start_time, end_time, club_id, club_name, publisher_id, publisher_name, max_participants, current_participants, status, create_time)
SELECT '校园诗歌朗诵大赛', '以"青春·梦想"为主题的诗歌朗诵比赛，欢迎所有热爱文学的同学参加。设一等奖1名，二等奖2名，三等奖3名。', '学生活动中心', DATE_ADD(NOW(), INTERVAL 14 DAY), DATE_ADD(NOW(), INTERVAL 14 DAY), 2, '文学社', 1, 'admin', 30, 8, 0, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_activity WHERE title = '校园诗歌朗诵大赛');

INSERT INTO t_activity (title, content, location, start_time, end_time, club_id, club_name, publisher_id, publisher_name, max_participants, current_participants, status, create_time)
SELECT '校际友谊篮球赛', '与邻校篮球社的友谊赛，欢迎同学们来现场加油助威！', '校体育馆', DATE_ADD(NOW(), INTERVAL 3 DAY), DATE_ADD(NOW(), INTERVAL 3 DAY), 3, '篮球社', 1, 'admin', 20, 10, 0, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_activity WHERE title = '校际友谊篮球赛');

-- 插入示例资讯
INSERT INTO t_news (title, content, club_id, club_name, publisher_id, publisher_name, view_count, create_time)
SELECT '计算机协会在全国大学生程序设计大赛中喜获佳绩', '在刚刚结束的全国大学生程序设计大赛中，我校计算机协会代表队凭借出色的表现，斩获银奖一枚、铜奖两枚。这是我校在该赛事中取得的历史最好成绩！', 1, '计算机协会', 1, 'admin', 128, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_news WHERE title LIKE '计算机协会在全国%');

INSERT INTO t_news (title, content, club_id, club_name, publisher_id, publisher_name, view_count, create_time)
SELECT '文学社第十期社刊《青春笔记》正式发行', '经过两个月的精心筹备，文学社第十期社刊《青春笔记》正式与大家见面。本期收录了20篇优秀原创作品，涵盖散文、诗歌、小说等多种文体，欢迎到文学社办公室免费领取。', 2, '文学社', 1, 'admin', 86, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_news WHERE title LIKE '文学社第十期%');

INSERT INTO t_news (title, content, club_id, club_name, publisher_id, publisher_name, view_count, create_time)
SELECT '志愿者协会荣获"优秀志愿服务团队"称号', '在本年度市级志愿服务评选中，我校志愿者协会荣获"优秀志愿服务团队"荣誉称号。过去一年，协会累计组织志愿活动50余次，参与志愿者超过500人次。', 4, '志愿者协会', 1, 'admin', 95, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_news WHERE title LIKE '志愿者协会荣获%');

-- 插入示例公告
INSERT INTO t_notice (title, content, publisher, top, view_count, create_time)
SELECT '关于2026年春季社团招新的通知', '各社团注意：2026年春季社团招新工作即将开始，请各社团负责人于3月1日前提交招新方案至学生处审批。招新时间统一安排在3月10日-3月20日。', 'admin', 1, 200, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_notice WHERE title LIKE '关于2026年春季%');

INSERT INTO t_notice (title, content, publisher, top, view_count, create_time)
SELECT '校园社团活动场地预约须知', '为规范社团活动场地使用，现将场地预约流程通知如下：1.登录校园管理系统提交申请；2.提前3个工作日预约；3.活动结束后恢复场地原貌。详情请咨询学生处。', 'admin', 0, 150, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_notice WHERE title LIKE '校园社团活动场地%');

INSERT INTO t_notice (title, content, publisher, top, view_count, create_time)
SELECT '关于社团经费申请的通知', '各社团可于每学期初向学生处提交活动经费申请，需提供详细的活动方案和预算清单。经费审批结果将在提交后5个工作日内公布。', 'admin', 0, 120, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_notice WHERE title LIKE '关于社团经费%');

-- 插入更多测试用户作为社团成员
INSERT INTO t_user (username, password, real_name, phone, email, gender, department, student_no, role, create_time)
SELECT 'wangwu', '123456', '王五', '13800000003', 'wangwu@campus.com', '男', '体育学院', '2024003', 0, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'wangwu');

INSERT INTO t_user (username, password, real_name, phone, email, gender, department, student_no, role, create_time)
SELECT 'zhaoliu', '123456', '赵六', '13800000004', 'zhaoliu@campus.com', '女', '外语学院', '2024004', 0, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'zhaoliu');

INSERT INTO t_user (username, password, real_name, phone, email, gender, department, student_no, role, create_time)
SELECT 'sunqi', '123456', '孙七', '13800000005', 'sunqi@campus.com', '男', '数学学院', '2024005', 0, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'sunqi');

INSERT INTO t_user (username, password, real_name, phone, email, gender, department, student_no, role, create_time)
SELECT 'zhouba', '123456', '周八', '13800000006', 'zhouba@campus.com', '女', '艺术学院', '2024006', 0, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'zhouba');

INSERT INTO t_user (username, password, real_name, phone, email, gender, department, student_no, role, create_time)
SELECT 'lisi', '123456', '李四', '13800000007', 'lisi@campus.com', '女', '文学院', '2024007', 0, NOW()
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_user WHERE username = 'lisi');

-- 插入社团成员数据
-- 计算机协会成员
INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'zhangsan', '张三', c.id, '计算机协会', '社长', 1, DATE_SUB(NOW(), INTERVAL 30 DAY)
FROM t_user u, t_club c WHERE u.username = 'zhangsan' AND c.name = '计算机协会'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'zhangsan' AND club_name = '计算机协会');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'wangwu', '王五', c.id, '计算机协会', '副社长', 1, DATE_SUB(NOW(), INTERVAL 25 DAY)
FROM t_user u, t_club c WHERE u.username = 'wangwu' AND c.name = '计算机协会'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'wangwu' AND club_name = '计算机协会');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'sunqi', '孙七', c.id, '计算机协会', '成员', 1, DATE_SUB(NOW(), INTERVAL 20 DAY)
FROM t_user u, t_club c WHERE u.username = 'sunqi' AND c.name = '计算机协会'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'sunqi' AND club_name = '计算机协会');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'zhaoliu', '赵六', c.id, '计算机协会', '成员', 1, DATE_SUB(NOW(), INTERVAL 15 DAY)
FROM t_user u, t_club c WHERE u.username = 'zhaoliu' AND c.name = '计算机协会'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'zhaoliu' AND club_name = '计算机协会');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'zhouba', '周八', c.id, '计算机协会', '成员', 1, DATE_SUB(NOW(), INTERVAL 10 DAY)
FROM t_user u, t_club c WHERE u.username = 'zhouba' AND c.name = '计算机协会'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'zhouba' AND club_name = '计算机协会');

-- 文学社成员
INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'lisi', '李四', c.id, '文学社', '社长', 1, DATE_SUB(NOW(), INTERVAL 28 DAY)
FROM t_user u, t_club c WHERE u.username = 'lisi' AND c.name = '文学社'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'lisi' AND club_name = '文学社');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'zhaoliu', '赵六', c.id, '文学社', '副社长', 1, DATE_SUB(NOW(), INTERVAL 22 DAY)
FROM t_user u, t_club c WHERE u.username = 'zhaoliu' AND c.name = '文学社'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'zhaoliu' AND club_name = '文学社');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'zhouba', '周八', c.id, '文学社', '成员', 1, DATE_SUB(NOW(), INTERVAL 18 DAY)
FROM t_user u, t_club c WHERE u.username = 'zhouba' AND c.name = '文学社'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'zhouba' AND club_name = '文学社');

-- 篮球社成员
INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'wangwu', '王五', c.id, '篮球社', '社长', 1, DATE_SUB(NOW(), INTERVAL 30 DAY)
FROM t_user u, t_club c WHERE u.username = 'wangwu' AND c.name = '篮球社'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'wangwu' AND club_name = '篮球社');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'zhangsan', '张三', c.id, '篮球社', '成员', 1, DATE_SUB(NOW(), INTERVAL 20 DAY)
FROM t_user u, t_club c WHERE u.username = 'zhangsan' AND c.name = '篮球社'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'zhangsan' AND club_name = '篮球社');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'sunqi', '孙七', c.id, '篮球社', '成员', 1, DATE_SUB(NOW(), INTERVAL 15 DAY)
FROM t_user u, t_club c WHERE u.username = 'sunqi' AND c.name = '篮球社'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'sunqi' AND club_name = '篮球社');

-- 志愿者协会成员
INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'zhaoliu', '赵六', c.id, '志愿者协会', '社长', 1, DATE_SUB(NOW(), INTERVAL 30 DAY)
FROM t_user u, t_club c WHERE u.username = 'zhaoliu' AND c.name = '志愿者协会'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'zhaoliu' AND club_name = '志愿者协会');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'lisi', '李四', c.id, '志愿者协会', '副社长', 1, DATE_SUB(NOW(), INTERVAL 25 DAY)
FROM t_user u, t_club c WHERE u.username = 'lisi' AND c.name = '志愿者协会'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'lisi' AND club_name = '志愿者协会');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'sunqi', '孙七', c.id, '志愿者协会', '成员', 1, DATE_SUB(NOW(), INTERVAL 20 DAY)
FROM t_user u, t_club c WHERE u.username = 'sunqi' AND c.name = '志愿者协会'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'sunqi' AND club_name = '志愿者协会');

INSERT INTO t_member (user_id, username, real_name, club_id, club_name, position, status, join_time)
SELECT u.id, 'zhouba', '周八', c.id, '志愿者协会', '成员', 1, DATE_SUB(NOW(), INTERVAL 15 DAY)
FROM t_user u, t_club c WHERE u.username = 'zhouba' AND c.name = '志愿者协会'
AND NOT EXISTS (SELECT 1 FROM t_member WHERE username = 'zhouba' AND club_name = '志愿者协会');

-- 插入示例留言
INSERT INTO t_message (content, user_id, username, reply, reply_by, reply_time, status, create_time)
SELECT '请问计算机协会什么时候开始招新？我很想加入！', 2, 'zhangsan', '你好！春季招新将在3月10日开始，届时请关注社团公告。', 'admin', NOW(), 1, DATE_SUB(NOW(), INTERVAL 2 DAY)
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_message WHERE content LIKE '请问计算机协会%');

INSERT INTO t_message (content, user_id, username, status, create_time)
SELECT '希望学校能多组织一些社团交流活动，让不同社团之间有更多互动的机会。', 2, 'zhangsan', 0, DATE_SUB(NOW(), INTERVAL 1 DAY)
FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM t_message WHERE content LIKE '希望学校能多组织%');
