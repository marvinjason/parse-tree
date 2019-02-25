package parsetree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser
{
    private List<String> list;
    private Map map;
    private String expr;
    
    public Parser()
    {
        this.list = new ArrayList();
        this.map = new HashMap();
    }
    
    public Node parse(String expression)
    {
        PostfixConverter converter = new PostfixConverter(remap(expression));
        System.out.println("Result: " + converter.infixToPostfix());
        expr = converter.infixToPostfix();
        return build();
    }
    
    private String remap(String expression)
    {
        char sub = 97;
        String newExpr = "";
        String str = "";
        
        for (int i = 0; i < expression.length(); i++)
        {
            char c = expression.charAt(i);
            
            if (Character.isDigit(c))
            {
                str += c;
            }
            
            if ((!Character.isDigit(c) && !str.isEmpty()) || i == expression.length() - 1)
            {
                map.put(Character.toString(sub), str);
                list.add(Character.toString(sub));
                
                newExpr += Character.toString(sub);
                
                if (!Character.isDigit(c))
                {
                    newExpr += c;
                }
                
                str = "";
                ++sub;
            }
            else if (!Character.isDigit(c))
            {
                newExpr += c;
            }
        }
        
        return newExpr;
    }
        
    private Node build()
    {
        Node node = new Node();
        node.value = Character.toString(expr.charAt(expr.length() - 1));
        
        if (!Character.isAlphabetic(expr.charAt(expr.length() - 1)))
        {
            expr = expr.substring(0, expr.length() - 1);
            node.right = build();
            node.left = build();
        }
        else
        {
            expr = expr.substring(0, expr.length() - 1);
        }
        
        return node;
    }
    
    public String getValue(String key)
    {
        return (String) map.get(key);
    }
}
