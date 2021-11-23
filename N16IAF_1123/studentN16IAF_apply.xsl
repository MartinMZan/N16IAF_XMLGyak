<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>Hallgatok apple-template</h2>
	
	<xsl:for-each select="class/student">
        
          ID: <xsl:value-of select="@id"/> <br/>
          Vezeteknev: <span style="color:green"><xsl:value-of select="vezeteknev"/> </span> <br/>
          Keresztnev: <span style="color:red"><xsl:value-of select="keresztnev"/> </span> <br/>
          <xsl:value-of select="becenev"/> Kor: <span style="color:blue"> <xsl:value-of select="kor"/> </span> <br/>
          Fizetes: <span style="color:red"> <xsl:value-of select="fizetes"/> </span> <br/>
        
        <br/>
        
      </xsl:for-each>
      
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>