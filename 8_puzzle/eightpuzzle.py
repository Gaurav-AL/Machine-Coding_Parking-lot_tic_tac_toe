class PuzzleConfig:
    puzzle,levels,heuristics = [],0,0
    def __init__(self,puzzle):
        self.puzzle = puzzle
class EightPuzzle:
    def changeval(self,lis):
        nlis = [x for x in lis]
        nlis[0] = 20
lis = [10,20,30]
ep = EightPuzzle()
ep.changeval(lis)
print(lis)
    
