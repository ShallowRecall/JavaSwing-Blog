package Test;

import com.zyproject.Dao.SentenceDao;
import com.zyproject.model.Sentence;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        SentenceDao sentenceDao=new SentenceDao();
        Sentence sentence=sentenceDao.getSentence();
        Random random=new Random();
        int index=random.nextInt(40)+1;
        System.out.println(sentence.getContent());

    }
}
