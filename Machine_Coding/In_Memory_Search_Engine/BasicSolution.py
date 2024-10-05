from collections import defaultdict
from In_Memory_Search_Engine.OptimizingSolution import Trie
class SearchEngine:
    def __init__(self):
        self.dataset = defaultdict(str)
        self.document = ''
    
    def searchPattern(self,search_pattern):
        all_search_words = [words for words in search_pattern.split(' ')]
        total_words,res = len(all_search_words),[]
        for dataset_name, doc in self.dataset.items():
            curr_count = 0
            for word in all_search_words:
                if(word in doc):
                    curr_count += 1
            if(curr_count == total_words):
                res.append(dataset_name)
        return res
                            
    def addDocuments(self, dataset, document):
        if(dataset not in self.dataset):
            self.dataset[dataset] = ''
        self.dataset[dataset] = document

SearchEngineObject = SearchEngine()
SearchEngineObjectTrie = Trie()
SearchEngineObject.addDocuments('Doc1','apple is a fruit')
SearchEngineObject.addDocuments('Doc2','apple apple come on')
SearchEngineObject.addDocuments('Doc3','oranges are sour')
SearchEngineObject.addDocuments('Doc4','apple is sweet')
SearchEngineObject.addDocuments('Doc5','veggies are healthy')

res = SearchEngineObject.searchPattern('apple is')
print(res)