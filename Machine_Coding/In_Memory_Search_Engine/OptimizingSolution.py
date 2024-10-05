# Storing sentences in a Trie can make search operations more efficient when you're searching for exact sentence prefixes. 
# However, in your case, the problem involves searching for patterns where words can appear in any order, and all words must be present to match a document. 
# For this use case, a Trie won't be as efficient or necessary, since it's designed to handle ordered sequences like words or characters in specific positions.

# Why Trie is Not Optimal for This Case:
# Unordered Search: A Trie excels when the search query involves sequences (like prefixes or exact matches).
# In your case, the search involves checking for the presence of words anywhere in the document, regardless of their order, 
# which does not align with how a Trie structures data.
# Efficiency in Word Matching: Using a Trie to match unordered words would involve traversing multiple branches for every word in a search query, leading to inefficiency.
# Optimizing Search with Better Approaches
# Instead of using a Trie, you can achieve better search efficiency using other data structures like inverted indices or hashmaps. 
# These structures are designed to efficiently search across large text datasets, even with unordered search terms.

# Optimized Approach: Using Inverted Index
# An inverted index is a data structure that maps words to the documents (or sentences) where they appear.
# This makes it very efficient to search for documents that contain specific words, even if the words appear in different orders.

# How Inverted Index Works:

# Index Creation: For each document, you break it into words and map each word to the document where it appears.
# This creates a dictionary where the key is a word and the value is a list of documents (or document IDs) containing that word.
# Search: When searching for a pattern, you retrieve the list of documents for each word in the search query and find the intersection (documents containing all the words).

from collections import defaultdict

class SearchEngine:
    def __init__(self):
        # Dictionary to store words and the documents they appear in
        self.inverted_index = defaultdict(set)  # Word -> Set of document IDs
        self.dataset = {}

    def insert(self, dataset: str, sentence: str):
        # Add document to dataset if not already present
        if dataset not in self.dataset:
            self.dataset[dataset] = []
        # Store the sentence in the dataset
        self.dataset[dataset].append(sentence.lower())
        
        # Add each word in the sentence to the inverted index
        doc_id = len(self.dataset[dataset]) - 1  # Document ID is the index in the dataset list
        words = sentence.lower().split()  # Split sentence into words
        for word in words:
            self.inverted_index[word].add((dataset, doc_id))  # Add dataset and doc_id to the word's set

    def search(self, query: str) -> list:
        # Search for documents containing all words in the query
        search_words = query.lower().split()
        
        if not search_words:
            return []
        
        # Get the sets of documents for each word in the query
        result_sets = [self.inverted_index[word] for word in search_words if word in self.inverted_index]
        
        if not result_sets:
            return []
        
        # Find the intersection of all result sets (documents containing all words)
        matching_docs = set.intersection(*result_sets) if result_sets else set()
        
        # Format the result as a list of document IDs grouped by dataset
        results_by_dataset = defaultdict(list)
        for dataset, doc_id in matching_docs:
            results_by_dataset[dataset].append(self.dataset[dataset][doc_id])  # Retrieve the actual document

        return results_by_dataset

# Example usage
search_engine = SearchEngine()
search_engine.insert('Doc1', 'apple is a fruit')
search_engine.insert('Doc2', 'apple apple come on')
search_engine.insert('Doc3', 'oranges are sour')
search_engine.insert('Doc4', 'apple is sweet')
search_engine.insert('Doc5', 'veggies are healthy')

# Search for documents containing both 'apple' and 'is'
results = search_engine.search('apple is')
print(results)
