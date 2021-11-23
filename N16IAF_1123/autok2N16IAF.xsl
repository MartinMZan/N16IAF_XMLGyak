<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
	
	<table>
	<xsl:for-each select="autok/auto">
      <xsl:if test="ar &gt; 30000">
      <tr>
        <td><xsl:value-of select="@rsz"/></td>
        <td><xsl:value-of select="ar"/></td>
      </tr>
      </xsl:if>
    </xsl:for-each>
    </table>
      
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>