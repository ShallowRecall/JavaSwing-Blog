/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : db_blog

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 17/12/2022 20:10:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for b_admin
-- ----------------------------
DROP TABLE IF EXISTS `b_admin`;
CREATE TABLE `b_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `QQ` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `phoneNumber` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of b_admin
-- ----------------------------
BEGIN;
INSERT INTO `b_admin` (`id`, `userName`, `password`, `age`, `QQ`, `mail`, `phoneNumber`) VALUES (1, 'admin', 'admin', 19, '1989424334', '1989424334@qq.com', '19308602513');
COMMIT;

-- ----------------------------
-- Table structure for b_article
-- ----------------------------
DROP TABLE IF EXISTS `b_article`;
CREATE TABLE `b_article` (
  `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发布作者',
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '文章标签',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文章类型',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文章标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '文章内容',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文章状态',
  `date` datetime DEFAULT NULL COMMENT '发布时间',
  `sorts_id` bigint DEFAULT NULL COMMENT '分类ID',
  PRIMARY KEY (`uid`),
  KEY `fk_article_sorts` (`sorts_id`),
  CONSTRAINT `fk_article_sorts` FOREIGN KEY (`sorts_id`) REFERENCES `b_sorts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of b_article
-- ----------------------------
BEGIN;
INSERT INTO `b_article` (`uid`, `author`, `title`, `type`, `name`, `content`, `status`, `date`, `sorts_id`) VALUES (1, 'qy', 'Java', '学习笔记', 'hello world', 'System.out.println(\"hello world\")', '公开', '2022-12-16 00:00:00', 1);
INSERT INTO `b_article` (`uid`, `author`, `title`, `type`, `name`, `content`, `status`, `date`, `sorts_id`) VALUES (2, 'qz', '日常', '日常分享', '日常', '今天是开心的一天', '公开', '1970-12-10 00:00:00', 2);
COMMIT;

-- ----------------------------
-- Table structure for b_labels
-- ----------------------------
DROP TABLE IF EXISTS `b_labels`;
CREATE TABLE `b_labels` (
  `label_id` bigint NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `label_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标签名称',
  `label_alias` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标签别名',
  `label_description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标签介绍',
  PRIMARY KEY (`label_id`),
  KEY `label_name` (`label_name`),
  KEY `label_alias` (`label_alias`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of b_labels
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for b_sentence
-- ----------------------------
DROP TABLE IF EXISTS `b_sentence`;
CREATE TABLE `b_sentence` (
  `id` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of b_sentence
-- ----------------------------
BEGIN;
INSERT INTO `b_sentence` (`id`, `content`) VALUES (2, '一本好书，就像高级武功秘籍一样，哪怕只是从里面领悟到个一招半势，功力提升起来都是惊人的。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (3, '今天的最后，是我最喜欢的一段话，每次看到都特别有感觉，喜欢这段话里那种感情，那种美好的景象，所以把它作为今天的结束。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (4, '程序员之所以犯错误，不是因为他们不懂，而是因为他们自以为什么都懂。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (5, '程序员中的前辈在谈到一个人学写程序有没有前途时，总是会说:“学程序不光要能吃苦能用功，还得看有没有sense');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (6, '就算我们站在群山之颠，也别忘记雄鹰依旧能从我们头顶飞过。骄傲是比用JAVA进行底层开发更可笑的东西。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (7, '无私奉献不是天方夜谭，有时候，我们也可以做到。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (8, '你的- -个程序有时正常有时不正常，而你已经完全遵循编程的规则，为什么?事实.上我认为相信只要遵循别人所说就能得到想当然的结果的人其实是个傻瓜。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (9, '硬的怕横的，横的怕不要命的，疯子都是不要命的，所以疯子力量大，程序员只有一种，疯狂的程序员。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (10, '调试完一 个动态连接函数，固然值得兴奋，但真正的成功远还在无数个函数之后。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (11, '程序员，他们想的是什么?他们想的永远都是技术，他们崇尚的也永远都是技术。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (12, '一个人静静坐在电脑面前写代码的感觉，那是什么感觉?那是武林高手闭关修炼的感觉。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (13, '如果你没有把握做到，最好就不要承诺，你什么也不承诺，至少别人不会看不起你。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (14, '没有情调，不懂浪漫，也许这是程序员的一面，但拥有朴实无华的爱是他们的另一面。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (15, '人呐，眼光放得长远一些，看到的东西也会多一些，生活也就会过得更有意义一点。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (16, '程序不是年轻的专利，但是，他属于年轻。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (17, 'IF(BOOL学习= =FAL SE)BOOL落后=TRUE;不断的学习，我们才能不断的前进。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (18, '对程序员来说大部分的快乐是从解决问题，特别是独立解决问题中获得，而不是从这个CASE有多大，奖金有多少中获得。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (19, '永不放弃，永不放弃又有两个原则，第一个原则是永不放弃，第二个原则就是:当你想放弃时回头看第一个原则。.');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (20, '程序是我的生命，但我相信爱她甚过爱我的生命。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (21, '你比他好一点，他不会承认你，反而会嫉妒你，只有你比他好很多，他才会承认你，然后还会很崇拜你，所以要做，就一定要比别人做得好很多。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (22, '作为一个真正的程序员，首先应该尊重编程，热爱你所写下的程序，他是你的伙伴，而不是工具。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (23, '编程是一-种单调的生活，因此程序员比普通人需要更多的关怀，更多的友情。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (24, '如果调试一个程序让你很苦恼，千万不要放弃，成功永远在拐角之后，除非你走到拐角，否则你永远不知道你离他多远，所以，请记住，坚持不懈，直到成功。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (25, '退一步海阔天空，这是一种应有的心境。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (26, '如果你喜欢底层开发，千万不要勉强自己去搞VC，找到你最真实的想法，程序员最不能忍受的就是万精油。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (27, '每一个问题都是一把锁，你要相信世界上一定有一把钥匙能打开这把锁， 你也能找到这把钥匙。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (28, '要么做第一个，要么做最好的一个。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (29, '非优秀的程序员常常把空间和时间消耗殆尽，优秀的程序员则总是有足够的空间和时间去完成编程任务，而且配合近乎完美。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (30, '这句话不是很文雅，彻底鄙视那些害怕别人超越自己而拒绝回答别人问题的程序员。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (31, '匹真正的好马，即使在鞭子的影子下，也能飞奔。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (32, '理想如果不向现实做一点点屈服，那么理想也将归于尘土。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (33, '编程中我们会遇到多少挫折?表放弃，沙漠尽头必是绿洲。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (34, '其实你找不到错误不代表错误不存在，同样你看不到技术比你牛的人并不代表世界上没有技术比你牛的人。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (35, 'IF(BOOL 学习==FAL .SE)BOOL落后=TRUE;不断的学习，我们才能不断的前进。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (36, '一个100行的代码调试都可能会让程序员遇到很多挫折，所以，面对挫折，我们永远不能低头。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (37, '我们应该重视团队的精神，一个人作用再大，也不过是一碗水中比较大的一粒水珠而已。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (38, '程序员是值得尊敬的，程序员的双手是魔术师的双手，他们把枯燥无味的代码变成了丰富多彩的软件。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (39, '程序员可以让步，却不可以退缩，可以羞涩，却不可以软弱，总之，程序员必须是勇敢的。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (40, '一个好汉三个帮，程序员同样如此。');
INSERT INTO `b_sentence` (`id`, `content`) VALUES (1, '信念和目标，必须永远洋溢在程序员内心。');
COMMIT;

-- ----------------------------
-- Table structure for b_set_artitle_label
-- ----------------------------
DROP TABLE IF EXISTS `b_set_artitle_label`;
CREATE TABLE `b_set_artitle_label` (
  `article_id` bigint NOT NULL AUTO_INCREMENT,
  `label_id` bigint NOT NULL,
  PRIMARY KEY (`article_id`),
  KEY `label_id` (`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of b_set_artitle_label
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for b_sorts
-- ----------------------------
DROP TABLE IF EXISTS `b_sorts`;
CREATE TABLE `b_sorts` (
  `id` bigint NOT NULL COMMENT '分类ID',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分类名称',
  `alias` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分类别名',
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分类描述',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `name` (`name`),
  KEY `alias` (`alias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of b_sorts
-- ----------------------------
BEGIN;
INSERT INTO `b_sorts` (`id`, `name`, `alias`, `description`) VALUES (1, '学习笔记', '学习', '用于存放学习笔记类文章');
INSERT INTO `b_sorts` (`id`, `name`, `alias`, `description`) VALUES (2, '日常分享', '日常', '用于存放日常生活类文章');
COMMIT;

-- ----------------------------
-- Table structure for b_user
-- ----------------------------
DROP TABLE IF EXISTS `b_user`;
CREATE TABLE `b_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `Sex` varchar(3) DEFAULT NULL,
  `QQ` varchar(100) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `phoneNumber` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `introduction` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of b_user
-- ----------------------------
BEGIN;
INSERT INTO `b_user` (`id`, `userName`, `password`, `age`, `Sex`, `QQ`, `mail`, `phoneNumber`, `title`, `introduction`) VALUES (2, 'zy', '1234', 19, '男', '3502134791', '3502134791@qq.com', '18571402513', '普通用户', '程序内容管理员');
INSERT INTO `b_user` (`id`, `userName`, `password`, `age`, `Sex`, `QQ`, `mail`, `phoneNumber`, `title`, `introduction`) VALUES (3, 'qz', '123456', 16, '女', '957217344', '957217344@qq.com', '15527672729', '普通用户', '内容创作者');
INSERT INTO `b_user` (`id`, `userName`, `password`, `age`, `Sex`, `QQ`, `mail`, `phoneNumber`, `title`, `introduction`) VALUES (4, 'qzy', '12345', 20, '男', '213131212312', '3242311234123', '12312312321', '普通用户', '普通用户');
COMMIT;

-- ----------------------------
-- Table structure for b_writer
-- ----------------------------
DROP TABLE IF EXISTS `b_writer`;
CREATE TABLE `b_writer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `QQ` varchar(100) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `phoneNumber` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `skill` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of b_writer
-- ----------------------------
BEGIN;
INSERT INTO `b_writer` (`id`, `userName`, `password`, `age`, `QQ`, `mail`, `phoneNumber`, `skill`) VALUES (1, 'zy', '123', 20, '3502134791', '3502134791@qq.com', '18571402513', '数据库，Java');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
