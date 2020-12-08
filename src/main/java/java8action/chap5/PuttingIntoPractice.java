package java8action.chap5;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice{
    public static void main(String ...args){    
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300), 
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),	
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

		// 找出2011年的所有交易并按交易额排序（从低到高）
        List<Transaction> collect = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println("2011年的所有交易并按交易额排序（从低到高）:" + Arrays.toString(collect.toArray()));

        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(tr2011);

        //--------------------------------------------------------------------------------------------------------------
        // 交易员都在那些不同的城市工作过
        Set<String> collect1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println("交易员都在那些不同的城市工作过:" + Arrays.toString(collect1.toArray()));

        // Query 2: What are all the unique cities where the traders work?
        List<String> cities =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(toList());
        System.out.println(cities);

        //-------------------------------------------------------------------------------------------------------------
        // 找到所有来自剑桥的交易员，并按姓名排序
        List<Transaction> collect2 = transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .sorted(comparing(transaction -> transaction.getTrader().getName()))
                .collect(toList());
        System.out.println("找到所有来自剑桥的交易员，并按姓名排序:" + Arrays.toString(collect2.toArray()));

        // Query 3: Find all traders from Cambridge and sort them by name.

        List<Trader> traders =
                transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader::getName))
                        .collect(toList());
        System.out.println(traders);

        // -------------------------------------------------------------------------------------------------------------

        // 返回交易员的姓名字符串，并按字母顺序排序
        String collect3 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(n1,n2) -> n1 + n2);
        System.out.println("返回交易员的姓名字符串，并按字母顺序排序:" + collect3);

        // Query 4: Return a string of all traders’ names sorted alphabetically.

        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        //--------------------------------------------------------------------------------------------------------------

        // 有没有交易员是在米兰工作的
        List<Transaction> collect4 = transactions.stream().filter(transaction -> "Milan".equals(transaction.getTrader().getCity())).collect(toList());
        System.out.println("有"+collect4.size()+"个交易员在米兰工作，他们是：" + Arrays.toString(collect4.toArray()));


        // Query 5: Are there any trader based in Milan?

        boolean milanBased =
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                .getCity()
                                .equals("Milan")
                        );
        System.out.println(milanBased);

        // -------------------------------------------------------------------------------------------------------------
        // 打印生活在剑桥的交易员的所有交易额
        Optional<Integer> reduce = transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity())).map(Transaction::getValue).reduce(Integer::sum);
        System.out.println("打印生活在剑桥的交易员的所有交易额:" + reduce.get());

        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(transactions);

        //-------------------------------------------------------------------------------------------------------------
        // 所有的交易中，最高的交易额是多少
        Optional<Transaction> max = transactions.stream().max(comparing(Transaction::getValue));
        System.out.println("所有的交易中，最高的交易额是多少:" + max.get());

        // Query 7: What's the highest value in all the transactions?
        int highestValue =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(0, Integer::max);
        System.out.println(highestValue);
        // ------------------------------------------------------------------------------------------------------------
        // 最小的交易额是多少
        Optional<Transaction> min = transactions.stream().min(comparing(Transaction::getValue));
        System.out.println("最小的交易额是多少:" + min.get());

































    }
}