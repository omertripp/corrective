cc = gcc
CC = g++

INCDIRS =
LIBDIRS =

LIBS =
CFLAGS = -g -Wall
PROG = aggregate

LATEX = latex

.SUFFIXES:
.SUFFIXES: .o .cc .fig .eps .ps .pdf .uu .dvi .tex .j

TEX := $(wildcard *.tex)
BIB := biblio.bib
FIG := $(wildcard *.fig)
EPS := $(FIG:.fig=.eps)
PS  = $(J:.j=.ps)

HDR = $(SRC:.cc=.hh)
OBJ = $(SRC:.cc=.o)

PAPER := paper
CONF := osdi06

all: paper.pdf
$(PAPER).dvi: $(TEX) $(EPS) $(PS) $(BIB) 

$(PROG): $(OBJ)
	$(CC) $(CFLAGS) $(LIBDIRS) -o $@ $(OBJ) $(LIBS)

.cc.o:
	$(CC) $(CFLAGS) $(INCDIRS) -c $< -o $@

# fig2eps is preferred because it allows LaTeX in labels
# my version also greps out '%%For' for anonymous submissions
.fig.eps:
#	fig2dev -L eps $< | grep -v "^%%For: " > $@
	./fig2eps $<
	@grep $@ .cvsignore || echo $@ >> .cvsignore

# -L eps is important.  -L ps loses bounding box info
#.fig.eps:
#	fig2dev -L eps $< > $@

.j.ps:
	jgraph $< > $@


RERUNTEX = "(undefined references|Rerun to get)"
RERUNBIB = "No file.*\.bbl|Citation.*undefined"

# Run latex; then bibtex and latex again for missing cites; then latex
# again for changed references.
.tex.dvi:
	$(LATEX) -halt-on-error $< -interaction=errorstopmode
	@echo "Considering bibtex rerun"
	@egrep $(RERUNBIB) $(<:.tex=.log) && (bibtex -min-crossrefs=100 $(<:.tex=); $(LATEX) -halt-on-error $< -interaction=errorstopmode); true
	@echo "Considering latex rerun"
	@egrep $(RERUNTEX) $(<:.tex=.log) && $(LATEX) -halt-on-error $< -interaction=errorstopmode; true
	@egrep -i "(Reference|Citation).*undefined" $*.log ; true
	@grep $@ .cvsignore || echo $@ >> .cvsignore

.dvi.ps:
	dvips -P cmz -G0 -t letter $< -o $@
	@grep $@ .cvsignore || echo $@ >> .cvsignore

.ps.pdf:
	ps2pdf $< $@


# -dMaxSubsetPct=100 -dEmbedAllFonts=true \
#	  -dCompatibilityLevel=1.4 -dPDFSETTINGS=/printer $< $@
#-dSubsetFonts=true 
	@grep $@ .cvsignore || echo $@ >> .cvsignore

$(PAPER): $(PAPER).tex
	latex2html -split 0 -noaddress -noinfo -show_section_numbers $<

clean:
	-rm -f *~  $(PAPER) core *.{dvi,aux,bbl,blg,log,bak} $(EPS)
	-rm -f $(PROG) *.o

tags:
	etags $(HDR) $(SRC)

SITE := jannotti.com:public_html/jannotti.com/papers
dist:	$(PAPER).ps $(PAPER).pdf $(PAPER)
	rsync $(PAPER).ps $(SITE)/$(PAPER)-$(CONF).ps
	rsync $(PAPER).pdf $(SITE)/$(PAPER)-$(CONF).pdf
	rsync -r $(PAPER)/* $(SITE)/$(PAPER)-$(CONF)
	echo $(SITE)/$(PAPER)-$(CONF) > dist

$(PAPER).dvi: $(EPS) $(PS)

