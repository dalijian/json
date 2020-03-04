class Node {
    root;
    children;
}

class Trie {
    constructor() {
        this.root = new Node("root");
    }

    insert(word) {
        var cur = this.root;
        for (var i = 0; i < word.length; i++) {
            var c = word[i];
            var node = cur.children[c];
            if (!node) {
                node = cur.children[c] = new Node(word[i]);
            }
            cur = node;
        }
        cur.pattern = word; //防止最后收集整个字符串用
        cur.endCount++; //这个字符串重复添加的次数
    }
}

//1. 对整个 字典树进行宽度优先遍历。
//2. 若当前搜索到点x,那么对于x的第i个儿子(也就是代表字符i的儿子)，一直往x的fail跳，直到跳到某个点也有i这个儿子，x的第i个儿子的fail就指向这个点的儿子i.
function createFail(ac) {
    var root = ac.root;
    var queue = [root]; //root所在层为第0层
    while (queue.length) {
        //广度优先遍历
        var node = queue.shift();
        if (node) {
            //将其孩子逐个加入列队
            for (var i in node.children) {
                var child = node.children[i];
                if (node === root) {
                    child.fail = root; //第1层的节点的fail总是指向root
                } else {
                    var p = node.fail; //第2层以下的节点, 其fail是在另一个分支上
                    while (p) {
                        //遍历它的孩子，看它们有没与当前孩子相同字符的节点
                        if (p.children[i]) {
                            child.fail = p.children[i];
                            break;
                        }
                        p = p.fail;
                    }
                    if (!p) {
                        child.fail = root;
                    }
                }
                queue.push(child);
            }
        }
    }
}

function match(ac, text) {
    var root = ac.root, p = root, ret = [], unique = {};
    for (var i = 0; i < text.length; i++) {
        var c = text[i];
        while (!p.children[c] && p != root) {
            p = p.fail; // 失配指针发挥作用 by 司徒正美
        }
        p = p.children[c];
        if (!p) {
            p = root; // 如果没有匹配的，从 root 开始重新匹配
        }
        var node = p;
        while (node != root) {
            //  
            if (node.endCount) {
                var pos = i - node.pattern.length + 1;
                console.log(`匹配模式串 ${node.pattern}其起始位置在${pos}`)
                if (!unique[node.pattern]) { //by 司徒正美
                    unique[node.pattern] = 1;
                    ret.push(node.pattern);
                }
            }
            node = node.fail;
        }
    }
    return ret;
}

function createGoto(trie, patterns) {
    for (var i = 0; i < patterns.length; i++) {
        trie.insert(patterns[i]);
    }
}

var ac = new Trie();
createGoto(ac, ["she", "shr", "say", "he", "her"]);
createFail(ac);
console.log(match(ac, "one day she say her has eaten many shrimps"));


function createFail(ac) {
    var root = ac.root;
    var queue = [root]; //root所在层为第0层
    while (queue.length) {
        //广度优先遍历
        var node = queue.shift();
        if (node) {
            //将其孩子逐个加入列队
            for (var i in node.children) {
                var child = node.children[i];
                if (node === root) {
                    child.fail = root; //第1层的节点的fail总是指向root
                } else {
                    var p = node.fail; //第2层以下的节点, 其fail是在另一个分支上
                    while (p) {
                        //遍历它的孩子，看它们有没与当前孩子相同字符的节点
                        if (p.children[i]) {
                            child.fail = p.children[i];
                            break;
                        }
                        p = p.fail;
                    }
                    if (!p) {
                        child.fail = root;
                    }
                }
                queue.push(child);
            }
        }
    }
}